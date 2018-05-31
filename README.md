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
<br> |view|   = <b><i>ui</b> / <b>rest</b> / <b>tree</b></i>
        <u>                                              </u>
       | ui |------|------| = data / <s> id </s> / <s> foreign </s> | 
       |----| rest |------| = data /  id  / <s> foreign </s> |
       |<u>----|------| tree | = data /  id  /  foreign  </u>|
<br>  (?)     = request parameters, <b><i>all optional</i></b>
 {body}   = request body / <b><i>json object</i></b>, <b><i>required</i></b>
{content} = <b><i>content-type: application/json; charset=UTF8</i></b>
 [auth]   = authorized user
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
                   ¦                ¦        ¦--me-|view|[auth]
                   ¦                ¦        ¦               
                   ¦                ¦        ¦               
                   ¦                ¦        ¦--one-|view|(?)
                   ¦                ¦        ¦               `
                   ¦                ¦        ¦            |i| ¦--id_branch
                   ¦                ¦        ¦            |s| ¦--number
                   ¦                ¦        ¦            's' '--name
                   ¦                ¦        ¦               
                   ¦                ¦        ¦--one-by-speciality-|view|(?)
                   ¦                ¦        ¦                             `
                   ¦                ¦        ¦                          |i| ¦--id_speciality
                   ¦                ¦        ¦                          |s| ¦--number
                   ¦                ¦        ¦                          's' '--name
                   ¦                ¦        ¦                              
                   ¦                ¦        ¦--one-by-user-|view|(?)
                   ¦                ¦        ¦                       `
                   ¦                ¦        ¦                    |i| ¦--id_user
                   ¦                ¦        ¦                    |i| ¦--id_contact_info
                   ¦                ¦        ¦                    |s| ¦--phone_number
                   ¦                ¦        ¦                    |s| ¦--email
                   ¦                ¦        ¦                    |i| ¦--id_study_info
                   ¦                ¦        ¦                    |i| ¦--id_scientific_links
                   ¦                ¦        ¦                    |s| ¦--orcid
                   ¦                ¦        ¦                    |s| ¦--researcherid
                   ¦                ¦        ¦                    |s| ¦--google_scholar_id
                   ¦                ¦        ¦                    's' '--scopus_author_id
                   ¦                ¦        ¦
                   ¦                ¦        '--all-|view|
                   ¦                ¦        
                   ¦                ¦--post--
                   ¦                ¦        `
                   ¦                ¦         '--add-|view|{body}{content}
                   ¦                ¦
                   ¦                ¦--put--
                   ¦                ¦       `
                   ¦                ¦        '--set-|view|(?){body}{content}
                   ¦                ¦                        `
                   ¦                ¦                     |i| ¦--id_speciality
                   ¦                ¦                     |s| ¦--number
                   ¦                ¦                     's' '--name
                   ¦                ¦
                   ¦                '--delete--
                   ¦                           `
                   ¦                            ¦--one-|view|(?)
                   ¦                            ¦               `
                   ¦                            ¦            |i| ¦--id_branch
                   ¦                            ¦            |s| ¦--number
                   ¦                            ¦            's' '--name
                   ¦                            ¦                
                   ¦                            '--one-by-speciality-|view|(?)
                   ¦                                                          `
                   ¦                                                       |i| ¦--id_speciality
                   ¦                                                       |s| ¦--number
                   ¦                                                       's' '--name
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