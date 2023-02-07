<script>
    let warning ="";
    let question = "";
    let answer = "";
    let choice1 = "";
    let choice2 = "";
    let choice3 = "";
    let choice4 = "";
    let type ="";
    let types =["FillInTheGap"];

    async function newQuestion (){
        if(answer == "" || question == "" || choice1 == "" || choice2 == "" || choice3 == "" || choice4 == "" || type ==""){
            warning = "Veuillez remplir tous les champs!";
        }
        else if (answer!=choice1 && answer!= choice2 && answer!=choice3 && answer!=choice4){
            warning = "La réponse ne fait pas partie des choix!";
        }
        else{
            warning ="";
            let data = {
                question: question,
                answer: answer,
                choices: `${choice1}|${choice2}|${choice3}|${choice4}`,
                type: type,
                set: {
                    id: 1
                }
            }
            console.log(JSON.stringify(data));
            response = await fetch('http://localhost:8080/api/admin/question', {
                method: 'POST',
                headers: {
                'Content-Type' : 'application/json',
                'Authorization': 'Basic YWRtaW46YWRtaW4=',
                },
                body: JSON.stringify(data)
            }).then(response => response.json())
        }
    }
</script>

<input name="question" type="question" placeholder="Entrez la question" bind:value={question}>
<input name="answer" type="answer" placeholder="Réponse à la question" bind:value={answer}>
<input name="choice1" type="choice1" placeholder="Proposition 1" bind:value={choice1}>
<input name="choice2" type="choice2" placeholder="Proposition 2" bind:value={choice2}>
<input name="choice3" type="choice3" placeholder="Proposition 3" bind:value={choice3}>
<input name="choice4" type="choice4" placeholder="Proposition 4" bind:value={choice4}>
<p>Type de question:</p>
<select bind:value={type}>
    {#each types as type}
        <option value={type}>
            {type}
        </option>
    {/each}
</select>
<br/>
<br/>
<button type="button" on:click={newQuestion}>Créer</button>
<p style="color:#FF0000">{warning}</p>