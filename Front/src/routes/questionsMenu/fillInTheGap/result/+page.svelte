<script>

let result;
if(typeof window !== 'undefined'){
    result = JSON.parse(localStorage.getItem('storable'))
}

console.log(result)
</script>

{#each result as results}

<p>data:{results}</p>
{/each}