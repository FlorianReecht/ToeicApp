import { writable } from "svelte/store";

const isConnected = typeof window !== "undefined";

const isLoggesInStorage = isConnected ? localStorage.getItem('isLoggedIn') === 'true' : false;

export const loggedIn = writable(isConnected, () => {
    console.log('got a sub');

    return () => console.log('no sub');
});

loggedIn.subscribe((value: boolean) => {
    if (isConnected) {
        localStorage.setItem('isLoggedIn', String(value))
    }
})