 <#import "parts/page.ftlh" as p>
<#import "parts/login.ftlh" as l>

<@p.page>
    <link rel="stylesheet" type="text/css" href="css/ConverterStyle.css">
    <div class="container">
                <h1>Конвертер</h1>
    <form action="/convert" method="post">
                    <label class="choice">
                        <select name="fromCode">
                            <#list curCode2Name as key, value>
                                <option value="${value}">${key}</option>
                            </#list>
                        </select>
                    </label>

                    <label class="choice">
                        <select name="toCode">
                            <#list curCode2Name as key, value>
                                <option value="${value}">${key}</option>
                            </#list>
                        </select>
                    </label>
                    <input type="number" min="0" name="from" class="result">
       <button type="submit">Конвертировать</button>
    </form>

<@l.logout/>
</@p.page>
</div>