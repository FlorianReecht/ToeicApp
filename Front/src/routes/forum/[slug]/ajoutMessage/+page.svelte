<script lang="ts">
  import { goto } from '$app/navigation';
    import { Input, Button } from 'sveltestrap'

let txt;
let id;
let password;
export let data;

if (typeof localStorage !== "undefined"){
    let user = JSON.parse(localStorage.getItem("store"));
    id = user.id;
    password = user.password;
  }

async function postMessage(){
    let dataBis = {
        content: txt,
        threadId: {
          id: data.params.slug
        },
        userId: {
          id: id,
          password: password
        }
      }
      fetch('http://localhost:8080/api/addMessage', {
        method: 'POST',
        headers: {
        'Content-Type' : 'application/json',
        },
        body: JSON.stringify(dataBis)
      }).then(response => response.json());

      goto(`/forum/${data.params.slug}`);
    }



</script>

<h1>Ajout d'un message</h1>

<p>id : {data.params.slug}</p>

<Input type='textarea' placeholder="message" bind:value={txt}/>
<Button on:click={postMessage}>Ajout message</Button>