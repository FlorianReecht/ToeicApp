import { goto } from '$app/navigation';
import type { PageLoad } from './$types';

export const load: PageLoad = async () => {
    if (localStorage.getItem('isLoggedIn') === 'true' && typeof localStorage !== "undefined"){
        goto('/')
      }
};