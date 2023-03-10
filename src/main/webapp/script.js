function addField(plusElement){

    let displayButton = document.querySelector("form button");

    // Stopping the function if the input field has no value.
    if(plusElement.previousElementSibling.value.trim() === ""){
        return false;
    }

    // creating the div container.
    let div = document.createElement("div");
    div.setAttribute("class", "field");

    // Creating the input element.
    let field = document.createElement("input");
    field.setAttribute("type", "text");
    field.setAttribute("name", "notes[]");

    // Creating the plus span element.
    let plus = document.createElement("span");
    plus.setAttribute("onclick", "addField(this)");
    let plusText = document.createTextNode("<button>Add more</button>");
    plus.appendChild(plusText);

    // Creating the minus span element.
    let minus = document.createElement("span");
    minus.setAttribute("onclick", "removeField(this)");
    let minusText = document.createTextNode("<button>Add more</button>");
    minus.appendChild(minusText);

    // Adding the elements to the DOM.
    form.insertBefore(div, displayButton);
    div.appendChild(field);
    div.appendChild(plus);
    div.appendChild(minus);

    // Un hiding the minus sign.
    plusElement.nextElementSibling.style.display = "block"; // the minus sign
    // Hiding the plus sign.
    plusElement.style.display = "none"; // the plus sign
}

function removeField(minusElement){
    minusElement.parentElement.remove();
}

let form = document.forms[0];
form.addEventListener("submit", fetchTextNotes);
function fetchTextNotes(event){
    // prevent the form to communicate with the server.
    event.preventDefault();

    // Fetch the values from the input fields.
    let data = new FormData(form);

    // Storing the values inside an array so we can handle them.
    // we don't want empty values.
    let notes = [];
    data.forEach( function(value){
        if(value !== ""){
            notes.push(value);
        }
    });

    // Output the values on the screen.
    let out = "";
    for(let note of notes){
        out += `
			<p>${note} <span onclick="markAsDone(this)">Mark as done</span></p>
		`;
    }
    document.querySelector(".notes").innerHTML = out;

    // Delete all input elements except the last one.
    let inputFields = document.querySelectorAll(".field");
    inputFields.forEach(function(element, index){
        if(index == inputFields.length - 1){
            element.children[0].value = "";
        }else{
            element.remove();
        }
    });
}

function markAsDone(element){
    element.classList.add("mark");
    element.innerHTML = "&check;";
}
// const addBtn = document.querySelector(".add");
// const input = document.querySelector(".inp-group");
// function removeInput() {
//     this.parentElement.remove();
// }
//
// document.getElementById("add").addEventListener("click", addInput() () );
// function addInput() {
//     const englishWord = document.createElement("input");
//     englishWord.type = "text";
//     englishWord.placeholder = "English word";
//     const uzbekWord = document.createElement("input");
//     uzbekWord.type = "text";
//     uzbekWord.placeholder = "Uzbek word";
//     const btn=document.createElement("a");
//     btn.className="delete";
//     btn.innerHTML="&times";
//     btn.addEventListener("click", removeInput);
//     const flex = document.createElement("div");
//     flex.className = "flex";
//     input.appendChild(flex);
//     flex.appendChild(englishWord);
//     flex.appendChild(uzbekWord);
//     flex.appendChild(btn);
// }
// addBtn.addEventListener("click", addInput);