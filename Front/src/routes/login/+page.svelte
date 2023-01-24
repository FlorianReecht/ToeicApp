<script>
  import { goto } from '$app/navigation';
  import {Buffer} from 'buffer';
  import { writable } from "svelte/store";
    /** @type {import('./$types').PageData} */
let users = [];
let name = ""
let password = ""
let admin = false
const store = writable();

async function login () {
  //On utilise un token Basic auth pour se connecter 
  if (localStorage.getItem('store') == null)
  {
  var hash = name + ":" + password;
  var token = Buffer.from(hash).toString('base64');
  var auto = "Basic " + token;
  console.log(auto);
  const res = await fetch('http://localhost:8080/api/public/login', {
    method: 'GET',
    headers: {
      'Content-Type' : 'application/json',
      'Authorization' : auto
    }
  })
  .then((response) => response.json())
  .then((data) => {
  console.log(data),
  store.subscribe(datas => localStorage.setItem('store', JSON.stringify(data)))});
  goto("/profil");
  }
  else {
    console.log("Déjà co");
  }
}


</script>

<h1> Page de connexion</h1>

<form>
    <input name="username" type="username" placeholder="Nom d'utilisateur" bind:value={name}>
    <input name="password" type="password" placeholder="Mot de passe" bind:value={password}>
    <button type="button" on:click={login}>Connexion</button>
  </form>

<p>Pas de compte ? Inscrivez-vous <a href="/register">ici</a></p>