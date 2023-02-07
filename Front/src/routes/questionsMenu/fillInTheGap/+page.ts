import type { PageLoad } from './$types';
import { writable } from 'svelte/store';

//We use the function load to do a GET fetch to retrieve threads 
export const load = (async ({fetch}) => {
    const res = await fetch('http://localhost:8080/api/question/type/FillInTheGap');
    const item = await res.json();

    return {item};

})

export function storable(data) {
    const store = writable(data);
    const { subscribe, set, update } = store;
    const isBrowser = typeof window !== 'undefined';

    isBrowser &&
        localStorage.storable &&
        set(JSON.parse(localStorage.storable));

    return {
        subscribe,
        set: n => {
            localStorage.storable = JSON.stringify(n);
        set(n);
        },
        update: cb => {
            const updatedStore = cb(localStorage.getItem('store'));
        localStorage.storable = JSON.stringify(updatedStore);
        set(updatedStore);
        }
    };
}