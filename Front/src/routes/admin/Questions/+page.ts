export const load = (async ({fetch}) => {
    const res = await fetch('http://localhost:8080/api/questions');
    const item = await res.json();

    return {item};
})