<script lang="ts">
    import { page } from '$app/stores'

    export let data;
    console.log(data)
</script>
    
    <h1>
    {#each data.item as thread}
        <tr>
            <a href="/forum/{thread.id}"> <td> Nom : {thread.content} crée par {thread.userId.name}</td></a>
        </tr>
    {/each}
    </h1>
    
    <tbody id = "forum">
    </tbody>

    <a href="forum/ajoutThread">Ajout</a>