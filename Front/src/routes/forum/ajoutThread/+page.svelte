<script script lang="ts">
  import { goto } from "$app/navigation";
  import {Button, Input, FormGroup, Label} from 'sveltestrap'

let name;
let id;
let password;
let txt;
let username;

if (typeof localStorage !== "undefined"){
    let user = JSON.parse(localStorage.getItem("store"));
    id = user.id;
    password = user.password;
    username = user.name;
  }

async function postThread(){
    let data = {
    content: name,
    userId: {
        id: id,
        name: username,
        password: password
    }
    
  }
  console.log(data);
    const response = await fetch('http://localhost:8080/api/AddThread', {
        method: 'POST',
        headers: {
        'Content-Type' : 'application/json',
        },
        body: JSON.stringify(data)
       
    }).then(response => response.json())
    .then(data => {
      let dataBis = {
        content: txt,
        threadId: {
          id: data.id
        },
        userId: {
          id: id,
          name: username,
          password: password
        }
      }
      fetch('http://localhost:8080/api/addMessage', {
        method: 'POST',
        headers: {
        'Content-Type' : 'application/json',
        },
        body: JSON.stringify(dataBis)
      })
    })
    goto("/forum");  
};
 
</script>

<h1> Ajout post forum </h1>

<FormGroup>
<Input name="thread" placeholder="Nom du thread" bind:value={name}/>
<Label>Premier message du topic : </Label>
<Input type="textarea" placeholder="Premier message" bind:value={txt}/> 
<Button type="button" on:click={postThread}>Création de thread</Button>
</FormGroup>