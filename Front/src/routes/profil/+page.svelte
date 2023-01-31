<script lang="ts">
  import { goto } from '$app/navigation';
  import { redirect } from '@sveltejs/kit';

  var user;

  if (typeof localStorage !== "undefined"){
    user = JSON.parse(localStorage.getItem("store"));
  }

function logout(){
    localStorage.clear();
    goto("/login");
}
</script>

<p>Connecté en tant que : {user.name}</p>

<button on:click={logout}>Déconnexion</button> 

<p>Pour changer de mot de passe : <a href="/profil/update"> ici </p>

