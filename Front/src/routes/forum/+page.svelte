<script lang="ts">
  import { goto } from '$app/navigation';
import { page } from '$app/stores'

export let data;
console.log(data.itemBis)
console.log(data.item)
</script>
    
    <h1>
    {#each data.item as thread, i}
        <tr>
            <a href="/forum/{thread.id}"> <td> Nom : {thread.content} crée par {thread.userId.name}</td></a>
            <p>Dernier message : {data.itemBis[i][0].content}</p>
        </tr>
    {/each}
    </h1>
    <a href="forum/ajoutThread">Ajout</a>