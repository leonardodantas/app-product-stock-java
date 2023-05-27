// meu_script.js

const fs = require('fs');
const { parseString } = require('xml2js');

// Função para ler o arquivo pom.xml
function readPomXml() {
  const pomXml = fs.readFileSync('pom.xml', 'utf8');
  return pomXml;
}

// Função para extrair as versões do arquivo pom.xml
function extractVersionsFromPomXml(pomXml) {
  return new Promise((resolve, reject) => {
    parseString(pomXml, (err, result) => {
      if (err) {
        reject(err);
      } else {
        const versions = [];
        const dependencies = result.project.dependencies[0].dependency;
        for (const dependency of dependencies) {
          const groupId = dependency.groupId[0];
          const artifactId = dependency.artifactId[0];
          const version = dependency.version[0];
          versions.push({ groupId, artifactId, version });
        }
        resolve(versions);
      }
    });
  });
}

console.log('Iniciando o script para verificar as versões do pom.xml...');

// Lê o arquivo pom.xml
const pomXml = readPomXml();

// Extrai as versões do arquivo pom.xml
extractVersionsFromPomXml(pomXml)
  .then((versions) => {
    console.log('Versões encontradas:');
    console.log(versions);
    console.log('Script concluído.');
  })
  .catch((error) => {
    console.error('Ocorreu um erro ao ler o pom.xml:', error);
  });
