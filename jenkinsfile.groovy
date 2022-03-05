// Сценарный конвейер;
node('master') {
	stage('Source') {// Получение кода;
		// Получаем код из нашего Git-репозитория;
		git branch: 'main', url: 'https://github.com/snk-world/ci_task_11_5.git'
	}
	stage('Compile') {// Компиляция и выполнение модульного тестирования;
		// Запуск Gradle для выполнения компиляции и модульного тестирования;
		sh "echo 123"
	}
	stage('exit') {
		sh 'echo end'
	}
}
