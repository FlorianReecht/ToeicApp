<script lang="ts">
    export let data; //We export the fetch result here with data from the page.ts load function
    import { goto } from '$app/navigation';
    import { storable } from './+page'

    let warning = "";
    let questions = [];
    let i = 0;
    let result = [];
    data.item.forEach(question => {
        questions[i]=question;
        questions[i].choices=question.choices.split("|");
        i++;
    });

    function handleChoice(choice, id){
        if (questions[id].answer === choice){
            result[id] = "Victory";
        }
        else{
            result[id] = "Faillure, correct answer was ";
            result[id] += questions[id].answer;
        }
    }

    function validate(){
        if (result.length == questions.length){
            warning = "";
            store.set(result);
            goto("/questionsMenu/fillInTheGap/result");
        }
        else{
            warning = "Veuillez répondre à toutes les questions.";
        }
    }

    const store = storable(result);
</script>

{#each questions as question, index}
    <p>{question.question}</p>
    {#each question.choices as choice}
    <li>
        <button type="button" on:click={() => handleChoice(choice, index)}>{choice}</button>
    </li>
    {/each}
{/each}

<br/><br/>
<button type="button" on:click={() => validate()}>Valider</button>
<p style="color:#FF0000">{warning}</p>