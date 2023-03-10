const quizData = [
    {
        question: "How are you1?",
        a: "good1",
        b: "good",
        c: "good",
        d: "good",
        correct: "good1",
    },
    {
        question: "How are you2?",
        a: "good2",
        b: "good",
        c: "good",
        d: "good",
        correct: "good2",
    },
    {
        question: "How are you?3",
        a: "good3",
        b: "good",
        c: "good",
        d: "good",
        correct: "good3",
    },
    {
        question: "How are you?4",
        a: "good4",
        b: "good",
        c: "good",
        d: "good",
        correct: "good4",
    },
    {
        question: "How are you?5",
        a: "good5",
        b: "good",
        c: "good",
        d: "good",
        correct: "good5",
    },
    {
        question: "How are you?6",
        a: "good6",
        b: "good",
        c: "good",
        d: "good",
        correct: "good6",
    },
    {
        question: "How are you?7",
        a: "good7",
        b: "good",
        c: "good",
        d: "good",
        correct: "good7",
    },
    {
        question: "How are you?8",
        a: "good8",
        b: "good",
        c: "good",
        d: "good",
        correct: "good8",
    },
    {
        question: "How are you?9",
        a: "good9",
        b: "good",
        c: "good",
        d: "good",
        correct: "good9",
    },
    {
        question: "How are you?",
        a: "good",
        b: "good",
        c: "good",
        d: "good",
        correct: "good",
    }
]
const answerEls = document.querySelectorAll('.answer')
const questionEl = document.getElementById('question')
const a_text = document.getElementById('a_text')
const b_text = document.getElementById('b_text')
const c_text = document.getElementById('c_text')
const d_text = document.getElementById('d_text')
const userId = document.getElementById('user_id').value
const submitBtn = document.getElementById('submit')
let currentQuiz = 0
let score = 0

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

    if (answer) {


        if (answer === quizData[currentQuiz].correct) {
            score++
        }

        currentQuiz++
        console.log(currentQuiz)
        if (currentQuiz < quizData.length) {
            loadQuiz()

        } else {
            fetch('http://localhost:8080/assesment', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },

                body: JSON.stringify({score})
            }).then(response => console.log('Success:'))

            quiz.innerHTML = `
          
            <h2>You answered   ${score}  / ${quizData.length} questions correctly</h2>
              <button href="/user" >Finish</button>
     
            `
        }
    }
})
