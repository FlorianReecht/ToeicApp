import type { PageLoad } from './$types';

export const load = (async ({fetch, params}) => {
    const res = await fetch('http://localhost:8080/api/Threads');
    const item = await res.json();
    return {item};

}) 