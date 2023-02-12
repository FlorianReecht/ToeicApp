import { error } from '@sveltejs/kit';

if (typeof localStorage !== "undefined" && localStorage.getItem("store") != null){
    var user = JSON.parse(localStorage.getItem("store"));
    var data = {
        id: user.id,
        name: user.name,
        password: user.password,
        admin: user.admin
    }
}else {
    // throw redirect(305, '/login')
}

let status;

export const load = (async ({ fetch }) => {
    const res = await fetch('http://localhost:8080/api/public/realUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => {
        status = response.status;
        if (status != 202) {
          throw error(302, "Cet utilisateur n'existe pas")
        }
    });
})