<script lang="ts">
export let data;

async function deleteQuestion(id){
    const res = await fetch(`http://localhost:8080/api/admin/deletequestion/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Basic YWRtaW46YWRtaW4='
        },
    }).then(response => response.json());
}

</script>

{#each data.item as question}
    <p>{question.question}</p>
    <p>{question.id}</p>
    
    <button on:click={ () => deleteQuestion(question.id)}>Supprimer</button> 
{/each}
