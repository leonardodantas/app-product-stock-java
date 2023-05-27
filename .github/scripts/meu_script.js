// meu_script.js

const fs = require('fs');
const xmlParser = require('xml-js');

// Função para ler o arquivo pom.xml
function readPomXml() {
  const pomXml = fs.readFileSync('pom.xml', 'utf8');
  return pomXml;
}

// Função para extrair as configurações do JaCoCo do arquivo pom.xml
function extractJacocoConfigFromPomXml(pomXml) {
  const pomJson = xmlParser.xml2js(pomXml, { compact: true });

  const plugins = pomJson.project.build.plugins.plugin;
  const jacocoPlugin = plugins.find(plugin => plugin.artifactId._text === 'jacoco-maven-plugin');

  if (!jacocoPlugin) {
    return null;
  }

  const jacocoConfig = jacocoPlugin.configuration;
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
