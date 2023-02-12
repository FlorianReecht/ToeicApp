<script lang="ts">
  import { Modal, ModalBody, ModalHeader} from 'sveltestrap';

export let data;
let mourir = new Array;
console.log(data.item)

async function getLast(id){
        const message = await fetch(`http://localhost:8080/api/messages/thread/${id}`);
        const test = await message.json();
        mourir.push(test)
        console.log('Dans fonc', test)
        return mourir;
  }


</script>
    
    <h1>
    {#each data.item as thread, i}
        <Modal static isOpen>
            <a href="/forum/{thread.id}"> <ModalHeader> {thread.content} crée par {thread.userId.name}</ModalHeader></a>
            <ModalBody> 
              {#await getLast(thread.id)}
              <p>KEK</p>
              {:then test}
              {#each test as message}
                 {message[0].content}
              {/each}
              {/await}</ModalBody>        
        </Modal>
    {/each}
    </h1>
    <a href="forum/ajoutThread">Ajout</a>