<script lang="ts">
  import { browser } from '$app/environment';
  import { goto } from '$app/navigation';
    import { page } from '$app/stores';
  import { loggedIn } from '$lib/stores/user';

    var user;
    
    if (browser){
  if (typeof localStorage !== "undefined"){
    user = JSON.parse(localStorage.getItem("store"));
  }
}
function logout(){
    localStorage.clear();
    loggedIn.set(false);
    goto("/login");
}

</script>

<h1>{$page.error?.message}</h1>

<button on:click={logout}>Déconnexion</button> 