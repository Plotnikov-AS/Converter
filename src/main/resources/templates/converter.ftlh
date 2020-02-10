<#import "parts/page.ftlh" as p>
<#import "parts/login.ftlh" as l>

<@p.page>
<link rel="stylesheet" type="text/css" href="css/ConverterStyle.css">
<div class="container">
    <h1>Конвертер</h1>
    <form action="/convert/save" method="post" class="form-one">
        <label class="choice">
            <select name="fromCode">
                <#list curCodes2Names as curCode2Name>
                    <option value="${curCode2Name['charcode']}">(${curCode2Name['charcode']}) ${curCode2Name['name']}</option>
                </#list>
            </select>
        </label>
        <label class="choice">
            <select name="toCode">
                <#list curCodes2Names as curCode2Name>
                    <option value="${curCode2Name['charcode']}">(${curCode2Name['charcode']}) ${curCode2Name['name']}</option>
                </#list>
            </select>
        </label>
        <input type="number" min="0" name="from" class="result">
        <button type="submit">Конвертировать</button>
    </form>
    <@l.logout/>
    </@p.page>
</div>