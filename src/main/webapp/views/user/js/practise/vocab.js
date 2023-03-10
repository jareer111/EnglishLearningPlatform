const quiz = document.getElementById('quiz')
const story_id = document.getElementById('story_id').value
const answerEls = document.querySelectorAll('.answer')
const questionEl = document.getElementById('question')
const a_text = document.getElementById('a_text')
const b_text = document.getElementById('b_text')
const c_text = document.getElementById('c_text')
const d_text = document.getElementById('d_text')
const userId = document.getElementById('user_id').value
const submitBtn = document.getElementById('submit')
const body = document.getElementById('body')
const current_quiz = document.getElementById('current_quiz')
let currentQuiz = 0
let score = 0
let vocabQuizData = (function getQuestions() {
    fetch('http://localhost:8080/vocabulary/get/test/' + story_id)
        .then(response => response.json())
        .then(json => {
            console.log(json)
            vocabQuizData = json
            loadQuiz()
        })
});
console.log(vocabQuizData)
body.onload=vocabQuizData()
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
    questionEl.innerText = vocabQuizData[currentQuiz].vocabulary
    a_text.innerText = vocabQuizData[currentQuiz].a
    b_text.innerText = vocabQuizData[currentQuiz].b
    c_text.innerText = vocabQuizData[currentQuiz].c
    d_text.innerText = vocabQuizData[currentQuiz].d
}


submitBtn.addEventListener('click', () => {
    let answer = getSelected()

    if (answer) {


        if (answer === vocabQuizData[currentQuiz].correct) {
            console.log(vocabQuizData[currentQuiz].correct)
            console.log(answer)
            score++
        }

        currentQuiz++
        console.log(currentQuiz)
        if (currentQuiz < vocabQuizData.length) {
            loadQuiz()

        } else {


            quiz.innerHTML = `
          
            <h2>You answered   ${score}  / ${vocabQuizData.length} questions correctly</h2>
              <button onclick="location.reload()" >Reload</button>
     
            `
        }
    }
})
