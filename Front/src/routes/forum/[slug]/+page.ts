import type { PageLoad } from './$types';

//We use the function load to do a GET fetch to retrieve threads 
export const load = (async ({fetch, params}) => {
    const res = await fetch(`http://localhost:8080/api/messages/thread/${params.slug}`);
    const item = await res.json();

    return {item};

})