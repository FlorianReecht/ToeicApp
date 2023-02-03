import { get } from "svelte/store";

import { loggedIn } from "./stores/user";

const isConnected = typeof window !== 'undefined';

export function authGuard({page}) : any {
    const isLoggedIn = get(loggedIn);

    if(!isConnected){
        return {};
    }

    if(isLoggedIn && page.path === 'login'){
        return {status: 302, redirect: '/'};
    } else if(!isLoggedIn && page.path !== '/login'){
        return {status:302, redirect: '/login'};
    }

    return {};
}