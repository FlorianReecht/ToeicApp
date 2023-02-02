<script script lang="ts">
  import { goto } from "$app/navigation";

let name;
let id;

if (typeof localStorage !== "undefined"){
    let user = JSON.parse(localStorage.getItem("store"));
    id = user.id;
    console.log(id)
  }

async function postThread(){
    let data = {
    content: name,
    userId: {
        id: id
    }
    
  }
  console.log(data);
    const response = await fetch('http://localhost:8080/api/AddThread', {
        method: 'POST',
        headers: {
        'Content-Type' : 'application/json',
        },
        body: JSON.stringify(data)
    })
    goto("/forum");
    return response.json();
   
};
    

    
</script>

<h1> Ajout post forum </h1>

<form>
<input name="thread" placeholder="Nom du thread" bind:value={name}>
<button type="button" on:click={postThread}>Création de thread</button>
</form>