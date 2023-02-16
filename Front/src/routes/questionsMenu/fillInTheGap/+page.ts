import type { PageLoad } from './$types';
import { writable } from 'svelte/store';

//We use the function load to do a GET fetch to retrieve threads 
export const load = (async ({fetch}) => {
    const res = await fetch('http://localhost:8080/api/question/type/FillInTheGap');
    const item = await res.json();
    console.log(item);
    return {item};

})
