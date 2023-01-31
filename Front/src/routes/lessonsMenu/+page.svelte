<script lang="ts">
    import Search from "svelte-search";
  
    let query = "";
    let value;
    let options = [
        { id: 1, text: '' },
		{ id: 2, text: 'Grammaire' },
		{ id: 3, text: 'Vocabulaire' },
		{ id: 4, text: 'Conjugaison' }
	];
	let selected = "";
    function handleSubmit(){
        if (selected != ''){
            console.log("submit", value, selected);
        }
        else{
            console.log("submit", value);
        }
    }
</script>

<form class = search>
    <Search label="Find a lesson " bind:value on:submit={() => handleSubmit()} />
    <select bind:value={selected}>
        {#each options as option}
            <option value={option.text}>
                {option.text}
            </option>
        {/each}
    </select>
    <button on:click={() => (value = "")}>Clear</button>
    <button on:click={() => handleSubmit()}>Search</button>

 
</form>

<p>{value}</p> 
<p>{selected}</p>



<style>
    .search{
        padding-top: 20px;
        width: 100%;
        display: flex;
        justify-content: start;
    }
</style>