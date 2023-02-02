<script lang=ts>
  import { goto } from '$app/navigation';
  import { error, redirect } from '@sveltejs/kit';
  import {Buffer} from 'buffer';
  import { writable } from "svelte/store";
let users = [];
let name = "";
let password = "";
let admin = false;
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
  .then((response) => {
    if (response.status == 401){
      var target = document.getElementById("error");
      target.innerHTML += "Mauvais mot de passe ou nom d'utilisateur"
      throw error(401, 'Wrong password or username');
    }
    else {
      return response.json();
    };}
  )
  .then((data) => {
  console.log(data),
  store.subscribe(datas => localStorage.setItem('store', JSON.stringify(data)))});
  goto("/profil");
  }
  else {
    console.log(":)")
  }
}


</script>

<h1> Page de connexion</h1>

<form>
  <input name="username" type="username" placeholder="Nom d'utilisateur" bind:value={name}>
  <input name="password" type="password" placeholder="Mot de passe" bind:value={password}>
  <button type="button" on:click={login}>Connexion</button>
 </form>

<p id="error"></p>

<p>Pas de compte ? Inscrivez-vous <a href="/register">ici</a></p>