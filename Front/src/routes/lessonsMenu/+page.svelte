<script>
    import Search from "svelte-search";
  
    let query = "";
    let value;
    let options = [
        { id: 1, text: '' },
		{ id: 2, text: 'Grammaire' },
		{ id: 3, text: 'Vocabulaire' },
		{ id: 4, text: 'Conjugaison' }
	];
	let selected="";

    async function handleSubmit(){
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
    <select value={selected}>
        {#each options as option}
            <option value={option}>
                {option.text}
            </option>
        {/each}
    </select>
    <button on:click={() => (value = "")}>Clear</button>
    <button on:click={() => handleSubmit()}>Search</button>
</form>



<style>
    .search{
        padding-top: 20px;
        width: 100%;
        display: flex;
        align-items: center;
        padding: 1em;
        position: absolute;
        padding-left: 20em;
        padding-right: 20em;
        justify-content: space-between;
    }
</style>