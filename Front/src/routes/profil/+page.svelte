<script>
  import { goto } from '$app/navigation';
  import { redirect } from '@sveltejs/kit';

function logout(){
    localStorage.clear();
    goto("/login");
}
</script>



<button on:click={logout}>Déconnexion</button> 
