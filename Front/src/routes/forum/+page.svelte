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
                    let slugs = "/forum/" + itemData.id;
                    temp += "<tr>"
                    temp += "<a href=" + '"' + slugs + '"' + "<td>" + "Nom : " + itemData.content + " crée par " + itemData.userId.name + "</td>" + "</a>";
                    temp += "</tr>";
                });
                document.getElementById('forum').innerHTML = temp;
            }
            }
        )
    });


</script>

    <tbody id = "forum">
    </tbody>
