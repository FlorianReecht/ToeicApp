<script lang="ts">
  import { goto } from '$app/navigation';
  import { redirect } from '@sveltejs/kit';
  import { loggedIn } from '$lib/stores/user';
  import { browser } from '$app/environment';

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

{#if user != null}
<p>Connecté en tant que : {user.name}</p>
{/if}


<button on:click={logout}>Déconnexion</button> 

<p>Pour changer de mot de passe : <a href="/profil/update"> ici </p>

