<script lang="ts">
    import Search from "svelte-search";
  import { writable } from "svelte/store";

    //export let data; We export the fetch result here with data from the page.ts load function
    let value;
    let title = [];
    const store = writable();
    let options = [
        { id: 1, text: '' },
		{ id: 2, text: 'Grammare' },
		{ id: 3, text: 'Vocabulary' },
		{ id: 4, text: 'Conjugaison' }
	];
	let selected = "";
    async function handleSubmit(){
        if (selected != '' && value !=""){
            const res = await fetch(`http://localhost:8080/api/lessons/typetitle/${selected}/${value}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {return response.json()})
            .then((data) =>  {
                var temp = "";
                data.forEach(itemData => {
                    temp += "<tr>";
                    temp += "<a href=" + '"' + itemData.link + '"' + '>' + "<td>" + itemData.title + "</td>" +"</a>";
                    temp += "</tr>";
                    temp += "<br/>";
            })
            document.getElementById('test').innerHTML = temp;    
            });
        }
        else if(value != ""){
            const res = await fetch(`http://localhost:8080/api/lessons/title/${value}`, {
            method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {return response.json()})
            .then((data) =>  {
                var temp = "";
                data.forEach(itemData => {
                    temp += "<tr>";
                    temp += "<a href=" + '"' + itemData.link + '"' + '>' + "<td>" + itemData.title + "</td>" +"</a>";
                    temp += "</tr>";
                    temp += "<br/>";
            })
            document.getElementById('test').innerHTML = temp;    
            });
        }
        else if(selected != ''){
            const res = await fetch(`http://localhost:8080/api/lessons/type/${selected}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {return response.json()})
            .then((data) =>  {
                var temp = "";
                data.forEach(itemData => {
                    temp += "<tr>";
                    temp += "<a href=" + '"' + itemData.link + '"' + "<td>" + itemData.title + "</td>" +"</a>";
                    temp += "</tr>";
                    temp += "<br/>";
            })
            document.getElementById('test').innerHTML = temp;    
            });
        }
        else{
            const res = await fetch(`http://localhost:8080/api/lessons`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {return response.json()})
            .then((data) =>  {
                console.log(data);
                var temp = "";
                data.forEach(itemData => {
                    temp += "<tr>";
                    temp += "<a href=" + '"' + itemData.link + '"' + "<td>" + itemData.title + "</td>" +"</a>";
                    temp += "</tr>";
                    temp += "<br/>";
            });
            document.getElementById('test').innerHTML = temp;    
            });
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

<div id="test"></div>

<style>
    .search{
        padding-top: 20px;
        width: 100%;
        display: flex;
        justify-content: start;
    }
</style>