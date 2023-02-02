<script lang="ts">
    import { page } from '$app/stores'

    const url = $page.url.toJSON();
    let id; //To not raise an error with item[id]
    id = url.split("forum/")
    export let data; //We export the fetch result here with data from the page.ts load function
    console.log(data)
</script>

<h1> Nom : {data.item[0].threadId.content}</h1>

<p>Message : {data.item[0].content} </p>

