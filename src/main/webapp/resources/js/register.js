let container = document.getElementById('container')

toggle = () => {
    // container.classList.toggle('sign-in')
    container.classList.toggle('sign-up')
    container.classList.toggle('sign-in')
}

setTimeout(() => {
    container.classList.add('sign-up')
}, 200)