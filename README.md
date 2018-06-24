# postgraduate-studies
<h2><b>RESTful API</b></h2>
<h4>Used technologies:</h5>
<ul>
<li>Kotlin
<li>PostgreSQL
<li>Spring Boot DevTools
<li>Spring Data JPA
<li>Spring Security
<li>Apache Tomcat
</ul>
<h4>API calls:</h4>
<pre><small><b><i><big>Parameters:</big></i></b>
<br> |-view|   = <b><i>ui</b> / <b>rest</b> / <b>tree</b></i>
         <u>                                         </u>
        | rest |------| = data /  id  / <s> foreign </s> |
        |<u>------| tree | = data /  id  /  foreign  </u>|
<br>  (?)     = request parameters, <b><i>all optional</i></b>
 {body}   = request body / <b><i>json object</i></b>, <b><i>required</i></b>
{content} = <i>content-type: application/json; charset=UTF8</i>, <b><i>required</i></b>
 [auth]   = authorized user, <b><i>required</i></b>
<br>|i| = integer
|s| = string
|b| = boolean</small><small>
<br><b><i><big>Map URL:</big></i></b>
        ---api---
                 `
                  `
                   ¦
                   ¦`
                   ¦ `--branches--
                   ¦              ` 
                   ¦               `
                   ¦                ¦--get--
                   ¦                ¦       `
                   ¦                ¦        ¦--me|-view|[auth]
                   ¦                ¦        ¦               
                   ¦                ¦        ¦               
                   ¦                ¦        ¦--one|-view|(?)
                   ¦                ¦        ¦             ¦
                   ¦                ¦        ¦      |s| -> ¦--number
                   ¦                ¦        ¦      |s| -> ¦--name
                   ¦                ¦        ¦      'i' -> '--id_branch
                   ¦                ¦        ¦               
                   ¦                ¦        ¦--one-by-speciality|-view|(?)
                   ¦                ¦        ¦                           ¦
                   ¦                ¦        ¦                    |s| -> ¦--number
                   ¦                ¦        ¦                    |s| -> ¦--name
                   ¦                ¦        ¦                    'i' -> '--id_speciality
                   ¦                ¦        ¦                              
                   ¦                ¦        ¦--one-by-user|-view|(?)
                   ¦                ¦        ¦                     ¦
                   ¦                ¦        ¦              |s| -> ¦--email
                   ¦                ¦        ¦              |s| -> ¦--phone_number
                   ¦                ¦        ¦              'i' -> '--id_user
                   ¦                ¦        ¦
                   ¦                ¦        '--all|-view|
                   ¦                ¦        
                   ¦                ¦--post--
                   ¦                ¦        `
                   ¦                ¦         '--add|-view|{body}{content}
                   ¦                ¦
                   ¦                ¦--put--
                   ¦                ¦       `
                   ¦                ¦        '--set|-view|(?){body}{content}
                   ¦                ¦                      ¦ 
                   ¦                ¦               |s| -> ¦--number
                   ¦                ¦               |s| -> ¦--name
                   ¦                ¦               'i' -> '--id_branch
                   ¦                ¦
                   ¦                '--delete--
                   ¦                           `
                   ¦                            ¦--one|-view|(?)
                   ¦                            ¦             ¦
                   ¦                            ¦      |s| -> ¦--number
                   ¦                            ¦      |s| -> ¦--name
                   ¦                            ¦      'i' -> '--id_branch
                   ¦                            ¦                
                   ¦                            '--one-by-speciality|-view|(?)
                   ¦                                                        ¦
                   ¦                                                 |s| -> ¦--speciality_number
                   ¦                                                 |s| -> ¦--speciality_name
                   ¦                                                 'i' -> '--id_speciality
                   ¦                        
                   ¦`
                   ¦ `---contact-info---
                   ¦                    `
                   ¦                     `
                   ¦
                   ¦`
                   ¦ `---degrees---
                   ¦               `
                   ¦                `
                   ¦
                   ¦`
                   ¦ `---departments---
                   ¦                   `
                   ¦                    `
                   ¦
                   ¦`
                   ¦ `---faculties---
                   ¦                 `
                   ¦                  `
                   ¦
                   ¦`
                   ¦ `---institutes---
                   ¦                  `
                   ¦                   `
                   ¦
                   ¦`
                   ¦ `---scientific-links---
                   ¦                        `
                   ¦                         `
                   ¦
                   ¦`
                   ¦ `---sections---
                   ¦                `
                   ¦                 `
                   ¦
                   ¦`
                   ¦ `---specialities---
                   ¦                    `
                   ¦                     `
                   ¦
                   ¦`
                   ¦ `---study-info---
                   ¦                  `
                   ¦                   `
                   ¦
                   ¦`
                   ¦ `---tasks---
                   ¦             `
                   ¦              `
                   '
                    `
                     `---users---
                                 `
                                  `
</small>
</pre>