<script lang="ts">
    import { page } from '$app/stores'

    const url = $page.url.toJSON();
    let id; //To not raise an error with item[id]
    id = url.split("forum/")
    export let data; //We export the fetch result here with data from the page.ts load function
</script>

<h1>{data.item[id[1]-1].content}</h1>

