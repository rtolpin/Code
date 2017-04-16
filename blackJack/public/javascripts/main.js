const typesOfCards = ['hearts', 'spades', 'diamonds', 'clover'];
const deck = ['2','3','4','5','6','7','8','9','J', 'Q', 'K', 'A'];
const numCards = 52;
let numCardsDrawn = 0;
let cardsDrawn = [];
let user = [];
let computer = [];
let totalUserSum = 0;
let totalComputerSum = 0;
let endOfGame = false;

function main(){
  var btn = document.querySelector(".playBtn");
  btn.addEventListener('click', function(evt){
    console.log('play button clicked');
    evt.preventDefault();
    console.log(this.classList);
    //document.getcardsDrawnByClassName("start")
    //var startForm = document.querySelector('.container');
    //console.log(this.classList);
    var startForm = document.querySelector('.container');
    console.log('14 ' + startForm.classList);
    startForm.classList.add('disappear');
    //console.log('14:', this.classList);
    console.log('17 ' + startForm.classList);
    //this.classList.toggle("start"); //this should refer to the element that was clicked on
    //this.classList.toggle("playBtn");
    var userInput = document.getElementById("startValues").value;
    console.log(userInput);

    let userInputArr = userInput.split(',');
    let x = 0;
    let y = 0;
    let currElement;

    for(let i = 0; i < userInputArr.length; ++i){
      console.log(x, y);
      var randFace = typesOfCards[Math.floor(Math.random() * typesOfCards.length)];
      console.log(randFace);
      currElement = randFace + ' ' + userInputArr[i];
      while(cardsDrawn.indexOf(currElement) != -1){
        randFace = typesOfCards[Math.floor(Math.random() * typesOfCards.length)];
        currElement = randFace + ' ' + userInputArr[i];
      }
      cardsDrawn[i] = randFace + ' ' + userInputArr[i];

      if(i % 2 == 0){
        computer[x] = cardsDrawn[i];
        x++;
        numCardsDrawn++;
      }else{
        user[y] = cardsDrawn[i];
        y++;
        numCardsDrawn++;
      }
      console.log(numCardsDrawn);
      console.log(cardsDrawn);
      if(numCardsDrawn > numCards){
        res.send('Error: Deck capacity exceeded');
      }
    }
    console.log(computer);
    console.log(user);

    var div = document.getElementById('blackjack');
    console.log(div);
    var compDiv = document.createElement('div');
    compDiv.classList.add('computer');
    //20px
    compDiv.setAttribute('style', 'margin-left: 270px; margin-top:1px; float:left;');
    compDiv.setAttribute('id', 'compHand');
    var userDiv = document.createElement('div');
    userDiv.classList.add('user');
    //208px
    //100
    userDiv.setAttribute('style', 'margin-left: 265px; margin-top:1px; float:left;');
    userDiv.setAttribute('id', 'yourHand');

    var compScoreDiv = document.createElement('div');
    compScoreDiv.classList.add('container');
    compScoreDiv.classList.add('score');
    compScoreDiv.classList.add('computer');
    var compPara = document.createElement('p');
    var compText = document.createTextNode('Computer Hand - Total: ?');
    compPara.setAttribute('id', 'computer_score');
    compPara.appendChild(compText);
    compScoreDiv.appendChild(compPara);
    //compScoreDiv.setAttribute('style', 'background-color:white;');
    div.appendChild(compScoreDiv);

    for(let j = 0; j < computer.length; ++j){
      var compCard = document.createElement('div');
      console.log(compCard);
      compCard.classList.add('computer');
      compCard.classList.add('card');
      if(j == 0){
        let compCardOneArr = computer[j].split(' ');
        if(compCardOneArr[1] == 'J' || compCardOneArr[1] == 'Q' || compCardOneArr[1] == 'K' || compCardOneArr[1] == 'A'){
          compCardOneArr[1] = convertFaceCard(compCardOneArr[1]);
        }
        compCard.classList.add(compCardOneArr[0]);
        compCard.classList.add('rank' + compCardOneArr[1]);
        compCard.classList.add('face');
      }else{
        compCard.classList.add('back');
      }
      //compCard.classList.add('back');
      //var left = 2;
      compCard.setAttribute('style', 'margin-top:20px;');
      //compCard.setAttribute("class", "card");
      console.log(compCard.classList);
      compDiv.appendChild(compCard);
    }
    div.appendChild(compDiv);


    for(let l = 0; l < user.length; ++l){
      let userArr = user[l].split(' ');
      if(userArr[1] == 'J' || userArr[1] == 'Q' || userArr[1] == 'K' || userArr[1] == 'A'){
        userArr[1] = convertFaceCard(userArr[1]);
      }
      totalUserSum += parseInt(userArr[1], 10);
    }

    var userScoreDiv = document.createElement('div');
    userScoreDiv.classList.add('container');
    userScoreDiv.classList.add('score');
    userScoreDiv.classList.add('user');
    var userPara = document.createElement('p');
    userPara.setAttribute('id', 'user_score');
    var userText = document.createTextNode('User Hand - Total:' + totalUserSum);
    userPara.appendChild(userText);
    userScoreDiv.appendChild(userPara);
    div.appendChild(userScoreDiv);


    for(let y = 0; y < user.length; ++y){
      let tokens = user[y].split(' ');
      if(tokens[1] == 'J' || tokens[1] == 'Q' || tokens[1] == 'K' || tokens[1] == 'A'){
        tokens[1] = convertFaceCard(tokens[1]);
      }

      var userCard = document.createElement('div');
      console.log(userCard);
      //userCard.classList.add('user');
      if(isNaN(tokens[1])){
        throw 'Could not parse token ' + token[1];
      }
      userCard.classList.add('card');
      userCard.classList.add(tokens[0]);
      userCard.classList.add('rank' + tokens[1]);
      userCard.classList.add('face');
      //userCard.classList.add('back');
      userCard.setAttribute('style', 'margin-top:5px;');
      console.log(userCard.classList);

      userDiv.appendChild(userCard);
    }
    div.appendChild(userDiv);

    var hitButton = document.createElement('input');
    hitButton.setAttribute('type', 'submit');
    hitButton.setAttribute('value', 'Hit');
    hitButton.setAttribute('id', 'hitButton');
    hitButton.classList.add('w3-button');
    hitButton.classList.add('w3-border-black');
    var standButton = document.createElement('input');
    standButton.setAttribute('type', 'submit');
    standButton.setAttribute('value', 'Stand');
    standButton.setAttribute('id', 'standButton');
    standButton.classList.add('w3-button');
    standButton.classList.add('w3-border-black');
    div.appendChild(hitButton);
    div.appendChild(standButton);

    playGame();

  });

}

function convertFaceCard(value){
  if(value == 'J'){
    return 11;
  }
  if(value == 'Q'){
    return 12;
  }
  if(value == 'K'){
    return 13;
  }
  if(value == 'A'){
    return 1;
  }
}

function playGame(){
  for(let j = 0; j < computer.length; ++j){
    let compArr = computer[j].split(' ');
    getComputerTotal(compArr[1]);
  }
  var hitBtn = document.querySelector("#hitButton");
  hitBtn.addEventListener('click', function(evt){
    evt.preventDefault();
    var div = document.getElementById('blackjack');
    var userDiv = document.querySelector('#yourHand');
    var compDiv = document.querySelector('#compHand');
    var userScoreDiv = document.querySelector('.container.score.user');
    var compScoreDiv = document.querySelector('.container.score.computer');
    console.log(userScoreDiv);

    var randType = typesOfCards[Math.floor(Math.random() * typesOfCards.length)];
    var randValue = deck[Math.floor(Math.random() * typesOfCards.length)];
    let currSelection = randType + ' ' + randValue;
    while(cardsDrawn.indexOf(currSelection) != -1){
      randType = typesOfCards[Math.floor(Math.random() * typesOfCards.length)];
      randValue = deck[Math.floor(Math.random() * typesOfCards.length)];
      currSelection = randType + ' ' + randValue;
    }
    user[user.length] = currSelection;
    cardsDrawn[cardsDrawn.length] = currSelection;
    console.log(cardsDrawn);
    let currSelectionArr = currSelection.split(' ');
    getNewUserTotal(currSelectionArr[1]);
    if(currSelectionArr[1] == 'J' || currSelectionArr[1] == 'Q' || currSelectionArr[1] == 'K' || currSelectionArr[1] == 'A'){
      currSelectionArr[1] = convertFaceCard(currSelectionArr[1]);
    }
    var oldUserScore = document.getElementById('user_score');
    console.log(oldUserScore);
    userScoreDiv.removeChild(oldUserScore);
    var newUserScore = document.createElement('p');
    var scoreText = document.createTextNode('User Hand - Total:' + totalUserSum);
    newUserScore.setAttribute('id', 'user_score');
    newUserScore.appendChild(scoreText);
    userScoreDiv.appendChild(newUserScore);

    var newCard = document.createElement('div');
    newCard.classList.add('card');
    newCard.classList.add(currSelectionArr[0]);
    newCard.classList.add('rank' + currSelectionArr[1]);
    newCard.classList.add('face');
    userDiv.appendChild(newCard);

    if(totalUserSum >21 || totalUserSum == 21){
      endOfGame = true;
    }
    if(endOfGame){
      var oldCompScore = document.getElementById('computer_score');
      console.log(oldCompScore);
      compScoreDiv.removeChild(oldCompScore);
      var compScore = document.createElement('p');
      var compScoreText = document.createTextNode('Computer Hand - Total:' + totalComputerSum);
      compScore.setAttribute('id', 'computer_score');
      compScore.appendChild(compScoreText);
      compScoreDiv.appendChild(compScore);

      for(let a = 1; a < computer.length; ++a){
        let compCardArr = computer[a].split(' ');
        var currCompCard = document.querySelector('.computer.card.back');
        currCompCard.classList.remove('back');
        currCompCard.classList.remove('computer');
        currCompCard.classList.add(compCardArr[0]);
        let rank = compCardArr[1];
        if(rank == 'J' || rank == 'Q' || rank == 'K' || rank == 'A')
          rank = convertFaceCard(rank);
        currCompCard.classList.add('rank' + rank);
        currCompCard.classList.add('face');
      }

      if(totalUserSum == 21 && totalComputerSum == 21){
        playerTies();
      }
      else if(totalUserSum > 21){
        playerLoses();
      }
      else if(totalUserSum == 21){
        playerWins();
      }
      else if(totalComputerSum > totalUserSum){
        let diffComp = Math.abs(21-totalComputerSum);
        let diffUser = Math.abs(21-totalUserSum);
        if(diffComp > diffUser){
          playerWins();
        }else{
          playerLoses();
        }
      }
    }


  });

  var standBtn = document.querySelector("#standButton");
  standBtn.addEventListener('click', function(evt){
    evt.preventDefault();
    var div = document.getElementById('blackjack');
    var compDiv = document.querySelector('#compHand');
    var compScoreDiv = document.querySelector('.container.score.computer');

    console.log(totalComputerSum);
    for(let b = 1; b < computer.length; ++b){
      let compArr = computer[b].split(' ');
      var currCard = document.querySelector('.computer.card.back');
      currCard.classList.remove('back');
      currCard.classList.remove('computer');
      currCard.classList.add(compArr[0]);
      if(compArr[1] == 'J' || compArr[1] == 'Q' || compArr[1] == 'K' || compArr[1] == 'A'){
        compArr[1] = convertFaceCard(compArr[1]);
      }
      currCard.classList.add('rank' + compArr[1]);
      currCard.classList.add('face');
    }

    while(totalComputerSum < 17){
      var randType = typesOfCards[Math.floor(Math.random() * typesOfCards.length)];
      var randValue = deck[Math.floor(Math.random() * typesOfCards.length)];
      let currSelection = randType + ' ' + randValue;
      while(cardsDrawn.indexOf(currSelection) != -1){
        randType = typesOfCards[Math.floor(Math.random() * typesOfCards.length)];
        randValue = deck[Math.floor(Math.random() * typesOfCards.length)];
        currSelection = randType + ' ' + randValue;
      }
      console.log(currSelection);
      computer[computer.length] = currSelection;
      cardsDrawn[cardsDrawn.length] = currSelection;
      console.log(cardsDrawn);
      let currSelectionArr = currSelection.split(' ');
      console.log(computer);
      getComputerTotal(currSelectionArr[1]);
      if(currSelectionArr[1] == 'J' || currSelectionArr[1] == 'Q' || currSelectionArr[1] == 'K' || currSelectionArr[1] == 'A'){
        currSelectionArr[1] = convertFaceCard(currSelectionArr[1]);
      }
      var compCard = document.createElement('div');
      console.log(compCard);
      compCard.classList.add('card');
      compCard.classList.add(currSelectionArr[0]);
      compCard.classList.add('rank' + currSelectionArr[1]);
      compCard.classList.add('face');
      compCard.setAttribute('style', 'margin-top:20px;');
      console.log(compCard.classList);
      compDiv.appendChild(compCard);
    }
    console.log(totalComputerSum);

    endOfGame = true;

    var oldCompScore = document.getElementById('computer_score');
    console.log(oldCompScore);
    compScoreDiv.removeChild(oldCompScore);
    var compScore = document.createElement('p');
    var compScoreText = document.createTextNode('Computer Hand - Total:' + totalComputerSum);
    compScore.setAttribute('id', 'computer_score');
    compScore.appendChild(compScoreText);
    compScoreDiv.appendChild(compScore);

    if(totalUserSum == 21){
      playerWins();
    }

    if(totalUserSum > 21){
      playerLoses();
    }

    if(totalComputerSum == totalUserSum){
      playerTies();
    }else if(totalComputerSum == 21){
        playerLoses();
    }else if(totalComputerSum >= totalUserSum){
      let diffComp = Math.abs(21-totalComputerSum);
      let diffUser = Math.abs(21-totalUserSum);

      if(diffComp < diffUser){
        playerLoses();
      }else{
        playerWins();
      }
    }else if(totalUserSum >= totalComputerSum){
      playerWins();
    }
  });

}

function playerWins(){
  var div = document.getElementById('blackjack');
  var userWinDiv = document.createElement('div');
  userWinDiv.classList.add('container');
  var userWinPara = document.createElement('p');
  var winText = document.createTextNode('You win!!!!!');
  userWinPara.appendChild(winText);
  userWinDiv.appendChild(userWinPara);
  userWinDiv.setAttribute('id', 'result');
  div.appendChild(userWinDiv);
}

function playerLoses(){
  var div = document.getElementById('blackjack');
  var userLoseDiv = document.createElement('div');
  userLoseDiv.classList.add('container');
  var userLosePara = document.createElement('p');
  //var strongTag = document.createElement('strong');
  var loseText = document.createTextNode('Player Lost :( (bust)');
  userLosePara.appendChild(loseText);
  userLoseDiv.appendChild(userLosePara);
  userLoseDiv.setAttribute('id', 'result');
  div.appendChild(userLoseDiv);
}

function playerTies(){
  var div = document.getElementById('blackjack');
  var compUserTieDiv = document.createElement('div');
  compUserTieDiv.classList.add('container');
  var compUserTiePara = document.createElement('p');
  var tieText = document.createTextNode('You Tied!');
  compUserTiePara.appendChild(tieText);
  compUserTieDiv.appendChild(compUserTiePara);
  compUserTieDiv.setAttribute('id', 'result');
  div.appendChild(compUserTieDiv);
}

function getNewUserTotal(cardVal){
  let diff = 21 - totalUserSum;
  if(cardVal == 'A'){
    if(diff < 11){
      totalUserSum += 1;
    }else{
      totalUserSum += 11;
    }
  }else if(cardVal == 'K' || cardVal == 'Q' || cardVal == 'J'){
    totalUserSum += convertFaceCard(cardVal);
  }else{
    totalUserSum += parseInt(cardVal, 10);
  }
}

function getComputerTotal(cardVal){
  let diff = 21 - totalComputerSum;
  if(cardVal == 'A'){
    if(diff < 11){
      totalComputerSum += 1;
    }else{
      totalComputerSum += 11;
    }
  }else if(cardVal == 'K' || cardVal == 'Q' || cardVal == 'J'){
    totalComputerSum += convertFaceCard(cardVal);
  }else{
    totalComputerSum += parseInt(cardVal, 10);
  }
}


document.addEventListener('DOMContentLoaded', main);
