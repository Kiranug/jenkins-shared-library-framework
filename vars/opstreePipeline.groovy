def call() {
pipeline {
    agent any  
           stage('Checkout') {
            checkout scm
        }

         def p = pipelineConfig()

        stages('Prerequistes'){
            serviceName = sh (
                    script: "echo ${p.SERVICE_NAME}|cut -d '-' -f 1",
                    returnStdout: true
                ).trim()
        

        stage('Build & Test') {
                sh "mvn --version"
                sh "mvn -Ddb_port=${p.DB_PORT} -Dredis_port=${p.REDIS_PORT} clean install"
        }
     }
} 
}
