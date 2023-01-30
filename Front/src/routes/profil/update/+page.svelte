<script lang="ts">
  import { writable } from 'svelte/store';

const user = JSON.parse(localStorage.getItem("store"));
const name = user.name;
const id = user.id;
let password = "";
const store = writable();    

async function update() {
  const res = await fetch('http://localhost:8080/api/public/update_user', {
    method: 'PUT',
    headers: {
      'Content-Type' : 'application/json',
    },
    body: JSON.stringify({
        id,
        name,
        password,
      })
  })
  .then((response) => response.json())
  .then((data) => {
  console.log(data),
  store.subscribe(datas => localStorage.setItem('store', JSON.stringify(data)))});
  }

</script>


<form> 
    <form>
        <input name="password" type="password" placeholder="Mot de passe" bind:value={password}>
        <button type="button" on:click={update}>Connexion</button>
      </form>
</form>
