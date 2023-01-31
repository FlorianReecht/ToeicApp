<script lang="ts">

    
    const forum = fetch('http://localhost:8080/api/Threads', {
        method: 'GET',
        headers: {
      'Content-Type' : 'application/json'
    }
    })
    .then((response) => {
        response.json().then(
            data => {
                if (typeof document !== "undefined"){
                var temp = "";
                data.forEach(itemData => {
                    temp += "<tr>";
                    temp += "<td>" + itemData.content + "</td>";
                });
                document.getElementById('forum').innerHTML = temp;
            }
            }
        )
    });


</script>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th> Nom : </th>
        </tr>
    </thead>
    <tbody id = "forum">

    </tbody>
    </table>
</div>