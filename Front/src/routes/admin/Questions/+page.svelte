<script lang="ts">
    import { Button, Table } from 'sveltestrap'


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

<Table striped>
    <thead>
        <tr> 
            <th>Question</th>
            <th>Réponse</th>
            <th>Choix</th>
            <th>Type</th>
            <th>Supprimer</th>
        </tr>
    </thead>
    <tbody>
{#each data.item as question}
    <tr>
        <th scope="row">{question.question}</th>
        <td>{question.answer}</td>
        <td>{question.choices}</td>
        <td>{question.type}</td> 
        <td><Button color="dark" on:click={ () => deleteQuestion(question.id)}>Supprimer</Button></td>
    </tr>
{/each}
    </tbody>
</Table>