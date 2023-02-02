<script lang="ts">  
    let options = [
        { id: 1, label: 'Texte à trou', href: './questionsMenu/fillInTheGap'},
	];
</script>

<form>
    {#each options as option}
        <li>
            <a href={option.href}>{option.label}</a>
        </li>
    {/each}
</form>