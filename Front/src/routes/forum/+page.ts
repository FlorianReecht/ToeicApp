import type { PageLoad } from './$types';

let itemBis = [""];
export const load = (async ({fetch, params}) => {
    const res = await fetch('http://localhost:8080/api/Threads');
    const item = await res.json();
    item.forEach(async element => {
        const message = await fetch(`http://localhost:8080/api/messages/thread/${element.id}`);
    itemBis.push(await message.json());
    });
    itemBis.shift()
    return {item, itemBis};

}) 