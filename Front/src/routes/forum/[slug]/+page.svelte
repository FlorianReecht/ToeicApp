<script lang="ts">
export let data; //We export the fetch result here with data from the page.ts load function
</script>

<h1>Titre du topic : {data.item[0].threadId.content}</h1>

<h3>
    {#each data.item as item}        
        <p>message : {item.content} par {item.userId.name}</p>
    {/each}
</h3>

<p><a href="/forum/{data.item[0].threadId.id}/ajoutMessage">Ajout message</a> </p>
