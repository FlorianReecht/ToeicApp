<script lang="ts">
    let questions = [
        { id: 1, question: 'Maybe ... too strict with him.', answer: 'you\'re', choices: ['your','you\'re','youre','yours']},
	];
    let result = "";

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