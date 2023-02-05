<script lang="ts">
    export let data; //We export the fetch result here with data from the page.ts load function

    let questions = [];
    let result = "";
    let i = 0;
    data.item.forEach(question => {
        questions[i]=question;
        questions[i].choices=question.choices.split("|");
        i++;
    });

    function handleChoice(choice, id){
        if (questions[id].answer === choice){
            result = "Victory";
        }
        else{
            result = "Faillure";
        }
    }
</script>

<p>{questions[0].question}</p>
{#each questions[0].choices as choice}
<li>
    <button type="button" on:click={() => handleChoice(choice, 0)}>{choice}</button>
</li>
{/each}

<p>{result}</p>