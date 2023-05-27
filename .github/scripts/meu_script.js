// meu_script.js

const fs = require('fs');

// Função para ler o arquivo pom.xml
function readPomXml() {
  const pomXml = fs.readFileSync('pom.xml', 'utf8');
  return pomXml;
}

// Função para extrair as configurações do JaCoCo do arquivo pom.xml
function extractJacocoConfigFromPomXml(pomXml) {
  const pomParser = new DOMParser();
  const pomDoc = pomParser.parseFromString(pomXml, 'application/xml');

  const jacocoPlugin = pomDoc.querySelector('plugin[artifactId="jacoco-maven-plugin"]');

  if (!jacocoPlugin) {
    return null;
  }

  const jacocoConfig = jacocoPlugin.querySelector('configuration');
  return jacocoConfig;
}

console.log('Iniciando o script para verificar as configurações do JaCoCo no pom.xml...');

// Lê o arquivo pom.xml
const pomXml = readPomXml();

// Extrai as configurações do JaCoCo do arquivo pom.xml
const jacocoConfig = extractJacocoConfigFromPomXml(pomXml);

if (jacocoConfig) {
  console.log('Configurações do JaCoCo encontradas:');
  console.log(jacocoConfig);
} else {
  console.log('Configurações do JaCoCo não encontradas no pom.xml.');
}

console.log('Script concluído.');
