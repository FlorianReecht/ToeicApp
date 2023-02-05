<script lang="ts">
    import Search from "svelte-search";

    export let data; //We export the fetch result here with data from the page.ts load function
    let value;
    let options = [
        { id: 1, text: '' },
		{ id: 2, text: 'Grammare' },
		{ id: 3, text: 'Vocabulary' },
		{ id: 4, text: 'Conjugaison' }
	];
	let selected = "";
    async function handleSubmit(){
        console.log(value);
        console.log(data.item);
        if (selected != '' && value !=""){
            const res = await fetch(`http://localhost:8080/api/lessons/typetitle/${selected}/${value}`);
            data = await res.json();
            console.log(data.item);
            console.log("1");
        }
        else if(value != ""){
            const res = await fetch(`http://localhost:8080/api/lessons/title/${value}`);
            data = await res.json();
            console.log(data.item);
            console.log("2");
        }
        else if(selected != ''){
            const res = await fetch(`http://localhost:8080/api/lessons/type/${selected}`);
            data = await res.json();
            console.log(data.item);
            console.log("3");
        }
        else{
            const res = await fetch(`http://localhost:8080/api/lessons`);
            data = await res.json();
            console.log(data.item);
            console.log("4");
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
    <button type="button" on:click={() => (value = "")}>Clear</button>
    <button type="button" on:click={() => handleSubmit()}>Search</button>
</form>
<p>{value}</p>

<form class = result>
    {#each data.item as result}
    <a href={result.link}>{result.title}</a><br/>   
    {/each}
</form>

<style>
    .search{
        padding-top: 20px;
        width: 100%;
        display: flex;
        justify-content: start;
    }
</style>