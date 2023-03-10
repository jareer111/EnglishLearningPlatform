const quiz = document.getElementById('quiz')
const grammar_id = document.getElementById('grammar_id').value
const answerEls = document.querySelectorAll('.answer')
const questionEl = document.getElementById('question')
const a_text = document.getElementById('a_text')
const b_text = document.getElementById('b_text')
const c_text = document.getElementById('c_text')
const d_text = document.getElementById('d_text')
const userId = document.getElementById('user_id').value
const submitBtn = document.getElementById('submit')
const body = document.getElementById('body')
let currentQuiz = 0
let score = 0
let quizData = (function getQuestions() {
    console.log(grammar_id)
    fetch('http://localhost:8080/questions/get/' + grammar_id)
        .then(response => response.json())
        .then(json => {
            console.log(json)
            quizData = json
            loadQuiz()
        })
});
body.onload=quizData()
function deselectAnswers() {
    answerEls.forEach(answerEl => answerEl.checked = false)
}

function getSelected() {
    let answer = null
    answerEls.forEach(answerEl => {
        if (answerEl.checked) {
            answer = answerEl.id
            console.log(answer)
        }
    })
    return answer
}


function loadQuiz() {
    deselectAnswers()
    questionEl.innerText = quizData[currentQuiz].question
    a_text.innerText = quizData[currentQuiz].a
    b_text.innerText = quizData[currentQuiz].b
    c_text.innerText = quizData[currentQuiz].c
    d_text.innerText = quizData[currentQuiz].d
}


submitBtn.addEventListener('click', () => {
    let answer = getSelected()
    let questionId = quizData[currentQuiz].questionId
    let isCorrect = quizData[currentQuiz].correct===answer
    let isLastQuestion = currentQuiz === quizData.length - 1
    fetch('http://localhost:8080/grammar/test', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({   userId ,questionId ,isCorrect , isLastQuestion})
    }).then(response => console.log('Success:'))
    if (answer) {


        if (answer === quizData[currentQuiz].correct) {
            score++
        }

        currentQuiz++
        console.log(currentQuiz)
        if (currentQuiz < quizData.length) {
            loadQuiz()

        } else {


            quiz.innerHTML = `
          
            <h2>You answered   ${score}  / ${quizData.length} questions correctly</h2>
              <button onclick="location.reload()" >Reload</button>
     
            `
        }
    }
})
