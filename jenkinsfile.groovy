// Сценарный конвейер;
node('master') {
	stage('Prepeare Source') {// Получение кода;
		git branch: 'main', url: 'https://github.com/snk-world/ci_task_HW-03'
	}
	stage('Update container') {
		sh "docker-compose up -d"
	}
	stage('clear workspace') {
		sh 'echo end'
	}
}
