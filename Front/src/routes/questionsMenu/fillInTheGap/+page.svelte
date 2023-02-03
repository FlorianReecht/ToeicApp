<script lang="ts">
    let questions = [];
    let result = "";

    function handleChoice(choice, id){
        if (questions[id].answer === choice){
            result = "Victory";
        }
        else{
            result = "Faillure";
        }
    }

    async function getQuestion (){
        const response = await fetch('http://localhost:8080/api/question/type/FillInTheGap', {
        method: 'GET',
        headers: {
        'Content-Type' : 'application/json',
        }
        }).then(response => response.json())
        .then(data => {
            let i = 0;
            data.forEach(question => {
                questions[i]=question;
                i++;
            });
        })
    }
</script>

<p>{questions[0].question}</p>
{#each questions[0].choices as choice}
<li>
    <button type="button" on:click={() => handleChoice(choice, 0)}>{choice}</button>
</li>
{/each}

<p>{result}</p>